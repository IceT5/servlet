package com.shuruitech.hypertelepathia.servlet.pojo;

/**
 * 
 * @author yangyuguang
 * @date 2019.4.23
 */
public class City {
    private String name;
    private String address;

    public City() {
    }

    public City(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "City [name=" + name + ", address=" + address + "]";
    }

}
