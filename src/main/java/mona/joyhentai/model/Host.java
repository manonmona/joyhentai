package mona.joyhentai.model;

public class Host {
    @Override
    public String toString() {
        return "Host{" +
                "id=" + id +
                ", host='" + host + '\'' +
                '}';
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.id
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.host
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    private String host;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.id
     *
     * @return the value of host.id
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.id
     *
     * @param id the value for host.id
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.host
     *
     * @return the value of host.host
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public String getHost() {
        return host;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.host
     *
     * @param host the value for host.host
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    public void setHost(String host) {
        this.host = host;
    }
}