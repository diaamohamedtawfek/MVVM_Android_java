
package com.mdiaa7788.mvvm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPosts {

    @SerializedName("body")
    @Expose
    private List<Body> body = null;
    @SerializedName("count")
    @Expose
    private Integer count;

    public List<Body> getBody() {
        return body;
    }

    public void setBody(List<Body> body) {
        this.body = body;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
