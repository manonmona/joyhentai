package mona.joyhentai.model;

public class Books {

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", pages=" + pages +
                ", createTime=" + createTime +
                ", status=" + status +
                ", src='" + src + '\'' +
                '}';
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column books.id
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column books.name
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column books.code
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column books.pages
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    private Integer pages;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column books.create_time
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    private Long createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column books.status
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column books.src
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    private String src;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column books.id
     *
     * @return the value of books.id
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column books.id
     *
     * @param id the value for books.id
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column books.name
     *
     * @return the value of books.name
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column books.name
     *
     * @param name the value for books.name
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column books.code
     *
     * @return the value of books.code
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column books.code
     *
     * @param code the value for books.code
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column books.pages
     *
     * @return the value of books.pages
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public Integer getPages() {
        return pages;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column books.pages
     *
     * @param pages the value for books.pages
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column books.create_time
     *
     * @return the value of books.create_time
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column books.create_time
     *
     * @param createTime the value for books.create_time
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column books.status
     *
     * @return the value of books.status
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column books.status
     *
     * @param status the value for books.status
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column books.src
     *
     * @return the value of books.src
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public String getSrc() {
        return src;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column books.src
     *
     * @param src the value for books.src
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public void setSrc(String src) {
        this.src = src;
    }
}