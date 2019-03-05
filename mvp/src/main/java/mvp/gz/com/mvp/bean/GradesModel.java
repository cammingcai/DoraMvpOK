package mvp.gz.com.mvp.bean;


import java.util.List;

/**
 * Created by camming on 2018\12\31 0031.
 * code is data  data is code
 * 年级
 */

public class GradesModel extends HttpModel {

    private List<Grades> data;

    public void setData(List<Grades> data) {
        this.data = data;
    }
    public List<Grades> getData() {
        return data;
    }

}
