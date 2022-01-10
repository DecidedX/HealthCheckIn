package decided.spider.healthcheckin.utils;

public enum MsgManager {

    LOGIN_FAILURE{
        public String toString() {
            return "登录失败";
        }
    },
    IS_CHECKED{
        public String toString() {
            return "已打卡";
        }
    },
    CHECK_IN_SUCCESS{
        public String toString() {
            return "打卡成功";
        }
    },
    CHECK_IN_FAILURE{
        public String toString() {
            return "打卡失败";
        }
    },
    WRITE_SUCCESS{
        public String toString() {
            return "写入成功";
        }
    },
    ACCOUNT_NOT_EXIST{
        public String toString() {
            return "用户不存在";
        }
    },
    ;

    public abstract String toString();
}
