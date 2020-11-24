package com.ruoyi.agent.domain;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/11/13 16:19
 */
@Data
public class DuYanUser {

    private int status;
    private DataBean data;

    @Data
    public static class DataBean {
        private int total_elements;
        private int total_pages;
        private List<AccountsBean> accounts;

        @Data
        public static class AccountsBean {
            private int account_id;
            private String name;
            private String mobile;
            private String title;
            private boolean is_fifo_agent;
            private boolean is_inspector;
            private boolean is_inspector_supervisor;
            private boolean is_admin;
            private boolean is_agent;
            private int team_id;
            private boolean is_supervisor;
        }
    }
}
