{
    int i;
    float x;
    boolean done;

    i = 0;
    x = 1.5;
    done = false;

    while (i < 10 && !done) {
        x = x * 2.0;
        i = i + 1;
        if (i == 5) {
            done = true;
        }
    }
}
