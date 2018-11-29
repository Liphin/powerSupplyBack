package example.operation.entity;

/**
 * Created by Administrator on 2018/9/15.
 */
public class Department {

    private int id;
    private int wx_dept_id;
    private String name;
    private int parentid;
    private int order;

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWx_dept_id() {
        return wx_dept_id;
    }

    public void setWx_dept_id(int wx_dept_id) {
        this.wx_dept_id = wx_dept_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", wx_dept_id=" + wx_dept_id +
                ", name='" + name + '\'' +
                ", parentid=" + parentid +
                ", order=" + order +
                '}';
    }
}
