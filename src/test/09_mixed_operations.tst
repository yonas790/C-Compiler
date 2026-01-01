{
    int i;
    float x;
    boolean done;

    i = 0;
    x = 1.5;
    done = false;

    while (i < 5 && !done) {
        x = x * 2.0;
        i = i + 1;

        if (i == 3) {
            float y;
            y = x / 2.0;

            {
                int z;
                z = i + 10;
                x = x + z;
            }
        } else {
            done = true;
        }
    }
}
