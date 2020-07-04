package ch.fronis.app.config;

import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class HeaderHelper {

    public static final int NEXT_REFRESH_SECONDS = 60 * 60;
    public static final int MAX_AGE_SECONDS = 24 * 60 * 60;

    private static final ThreadLocal<SimpleDateFormat> headerDateFormatter = new ThreadLocal<>();

    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String NEXT_REFRESH = "X-Next-Refresh";
    private static final String MAX_AGE = "max-age";


    public static <T> ResponseEntity<T> createOKResponseEntity(T body, int nextRefreshSeconds, int maxAgeSeconds) {
        return ResponseEntity.ok().header(CACHE_CONTROL, MAX_AGE + "=" + maxAgeSeconds).header(NEXT_REFRESH, createNextRefreshValue(nextRefreshSeconds)).body(body);
    }

    private static String createNextRefreshValue(int nextRefreshSeconds) {
        return MAX_AGE + "=" + formatHeaderDate(Date.from(Instant.now().plusSeconds(nextRefreshSeconds)));
    }

    private static String formatHeaderDate(Date date) {
        SimpleDateFormat sdf = headerDateFormatter.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            headerDateFormatter.set(sdf);
        }
        return sdf.format(date);
    }
}
