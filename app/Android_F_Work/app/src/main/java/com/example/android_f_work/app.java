package com.example.android_f_work;

public class app {
    private int ImageId;
    private String name;

    public app(int ImageId,String name)
    {
        this.ImageId = ImageId;
        this.name = name;
    }
    public int getImageId()
    {
        return this.ImageId;
    }
    public String getName()
    {
        return this.name;
    }
}
