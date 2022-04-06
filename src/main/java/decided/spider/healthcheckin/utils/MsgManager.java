package decided.spider.healthcheckin.utils;

public enum MsgManager {

    YIBAN_LOGIN_FAILURE{
        public String toString() {
            return "易班登录失败";
        }
    },
    LOGIN_FAILURE{
        public String toString() {
            return "打卡登录失败";
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
    ACCOUNT_EXISTED{
        public String toString() {
            return "用户已存在";
        }
    },
    ACCOUNT_REMOVE_SUCCESS{
        public String toString() {
            return "用户已删除";
        }
    },
    ;

    public abstract String toString();
}
