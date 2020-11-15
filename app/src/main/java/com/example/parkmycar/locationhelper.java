package com.example.parkmycar;

public class locationhelper {
    String name,description,capacity,latitude,longitude;
    byte[] image;
        public locationhelper() {

        }
        public locationhelper(String name,String description,String capacity,String latitude,String longitude,byte[] image) {
            this.name=name;
            this.description=description;
            this.capacity=capacity;
            this.latitude=latitude;
            this.longitude=longitude;
            this.image=image;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
