package com.example.android.xiaolv;

import java.util.List;

/**
 * @author furuibang
 * @time 15-11-25 下午2:41
 */
public class FenyeBean {

    /**
     * pageNum : 1
     * pageSize : 10
     * total : 2
     * pages : 1
     * list : [{"id":"other-cd2603ce134f4fffa5644e4286e90abf","name":"人人待","imgurl":"http://120.26.162.202/loans/pic/09a1690ee7f44bab943a189d27ea5a0f.jfif","usernum":11456,"describes":"一秒就放","classify":"hot","advertising":null,"producturl":"http://www.ppdai.com/landingbdpz.html?regsourceid=mktjcbdpzbiaoti&sourceid=17150&utm_source=baidubrand&utm_medium=cpt&utm_campaign=biaoti","slogan":"","createdate":"2019-04-08T04:29:30.000+0000"},{"id":"other-06c9b39005524286a9d2c81536a8594e","name":"拍拍贷","imgurl":"http://120.26.162.202/loans/pic/06e55095a9ee4efa89d605e483f0e4d8.jpg","usernum":9536,"describes":"急速放款","classify":"hot","advertising":null,"producturl":"http://www.ppdai.com/landingbdpz.html?regsourceid=mktjcbdpzbiaoti&sourceid=17150&utm_source=baidubrand&utm_medium=cpt&utm_campaign=biaoti","slogan":null,"createdate":"2019-04-08T04:28:33.000+0000"}]
     */

    private int pageNum;
    private int pageSize;
    private int total;
    private int pages;
    private List<ListBean> list;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : other-cd2603ce134f4fffa5644e4286e90abf
         * name : 人人待
         * imgurl : http://120.26.162.202/loans/pic/09a1690ee7f44bab943a189d27ea5a0f.jfif
         * usernum : 11456
         * describes : 一秒就放
         * classify : hot
         * advertising : null
         * producturl : http://www.ppdai.com/landingbdpz.html?regsourceid=mktjcbdpzbiaoti&sourceid=17150&utm_source=baidubrand&utm_medium=cpt&utm_campaign=biaoti
         * slogan :
         * createdate : 2019-04-08T04:29:30.000+0000
         */

        private String id;
        private String name;
        private String imgurl;
        private int usernum;
        private String describes;
        private String classify;
        private String advertising="";
        private String producturl;
        private String slogan;
        private String createdate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getUsernum() {
            return usernum;
        }

        public void setUsernum(int usernum) {
            this.usernum = usernum;
        }

        public String getDescribes() {
            return describes;
        }

        public void setDescribes(String describes) {
            this.describes = describes;
        }

        public String getClassify() {
            return classify;
        }

        public void setClassify(String classify) {
            this.classify = classify;
        }

        public String getAdvertising() {
            return advertising;
        }

        public void setAdvertising(String advertising) {
            this.advertising = advertising;
        }

        public String getProducturl() {
            return producturl;
        }

        public void setProducturl(String producturl) {
            this.producturl = producturl;
        }

        public String getSlogan() {
            return slogan;
        }

        public void setSlogan(String slogan) {
            this.slogan = slogan;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }
    }
}
