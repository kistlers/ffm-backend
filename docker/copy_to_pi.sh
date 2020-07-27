if [ $# -eq 0 ]; then
    echo "no argument supplied"
    exit
fi

docker save fronis-$1:0.0.1 | bzip2 | pv | ssh rpi 'bunzip2 | docker load'

