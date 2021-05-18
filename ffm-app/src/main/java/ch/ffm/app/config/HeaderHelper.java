package ch.ffm.app.config;

import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;

public class HeaderHelper {

    public static final int NEXT_REFRESH_SECONDS = 60 * 60;
    public static final int MAX_AGE_SECONDS = 24 * 60 * 60;

    private static final ThreadLocal<SimpleDateFormat> headerDateFormatter = new ThreadLocal<>();

    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String NEXT_REFRESH = "X-Next-Refresh";
    private static final String MAX_AGE = "max-age";

    private HeaderHelper() {
        // only static
    }

    public static <T> ResponseEntity<T> createOKResponseEntity(T body, int nextRefreshSeconds, int maxAgeSeconds) {
        return ResponseEntity.ok().header(CACHE_CONTROL, MAX_AGE + "=" + maxAgeSeconds).header(NEXT_REFRESH, createNextRefreshValue(nextRefreshSeconds)).body(body);
    }

    public static <T> ResponseEntity<T> createOKResponseEntityDefaultCacheControl(T body) {
        return createOKResponseEntity(body, NEXT_REFRESH_SECONDS, MAX_AGE_SECONDS);
    }

    private static String createNextRefreshValue(int nextRefreshSeconds) {
        return MAX_AGE + "=" + formatHeaderDate(Date.from(Instant.now().plusSeconds(nextRefreshSeconds)));
    }

    private static String formatHeaderDate(Date date) {
        var sdf = Optional.ofNullable(headerDateFormatter.get()).orElseGet(() -> {
            var simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            headerDateFormatter.set(simpleDateFormat);
            return simpleDateFormat;
        });
        return sdf.format(date);
    }
}
