package com.horen.cortp.company.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HOREN on 2017/10/17.
 */

public class EmptyBoxBean extends BaseResponse {

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    /**
     * data : {"ctnrType":[{"ctnrType":"IF1040","fullBox":0,"emptyBox":182,"boxSize":182},{"ctnrType":"OF1040","fullBox":0,"emptyBox":1,"boxSize":1},{"ctnrType":"OF330","fullBox":0,"emptyBox":2,"boxSize":2}],"ownerBox":3,"fullBox":0,"emptyBox":185,"boxSize":185,"ownerBoxDetails":{"":"Horen","CM00000230":"上海好成食品发展有限公司","CM00000235":"天津太阳食品有限公司"}}
     * header : {"srv_id":"","srv_desc":"","ret_status":"1","ret_value":"消息","ret_message":"获取数据成功."}
     */

    private DataBean data;

    public static class DataBean implements Serializable {
        /**
         * ctnrType : [{"ctnrType":"IF1040","fullBox":0,"emptyBox":182,"boxSize":182},{"ctnrType":"OF1040","fullBox":0,"emptyBox":1,"boxSize":1},{"ctnrType":"OF330","fullBox":0,"emptyBox":2,"boxSize":2}]
         * ownerBox : 3
         * fullBox : 0
         * emptyBox : 185
         * boxSize : 185
         * ownerBoxDetails : {"":"Horen","CM00000230":"上海好成食品发展有限公司","CM00000235":"天津太阳食品有限公司"}
         */

        private int ownerBox;
        private int fullBox;
        private int emptyBox;
        private int boxSize;
        private OwnerBoxDetailsBean ownerBoxDetails;
        private List<CtnrTypeBean> ctnrType;

        public int getOwnerBox() {
            return ownerBox;
        }

        public void setOwnerBox(int ownerBox) {
            this.ownerBox = ownerBox;
        }

        public int getFullBox() {
            return fullBox;
        }

        public void setFullBox(int fullBox) {
            this.fullBox = fullBox;
        }

        public int getEmptyBox() {
            return emptyBox;
        }

        public void setEmptyBox(int emptyBox) {
            this.emptyBox = emptyBox;
        }

        public int getBoxSize() {
            return boxSize;
        }

        public void setBoxSize(int boxSize) {
            this.boxSize = boxSize;
        }

        public OwnerBoxDetailsBean getOwnerBoxDetails() {
            return ownerBoxDetails;
        }

        public void setOwnerBoxDetails(OwnerBoxDetailsBean ownerBoxDetails) {
            this.ownerBoxDetails = ownerBoxDetails;
        }

        public List<CtnrTypeBean> getCtnrType() {
            return ctnrType;
        }

        public void setCtnrType(List<CtnrTypeBean> ctnrType) {
            this.ctnrType = ctnrType;
        }

        public static class OwnerBoxDetailsBean implements Serializable{
            /**
             *  : Horen
             * CM00000230 : 上海好成食品发展有限公司
             * CM00000235 : 天津太阳食品有限公司
             */

            @com.google.gson.annotations.SerializedName("")
            private String _$159; // FIXME check this code
            private String CM00000230;
            private String CM00000235;

            public String get_$159() {
                return _$159;
            }

            public void set_$159(String _$159) {
                this._$159 = _$159;
            }

            public String getCM00000230() {
                return CM00000230;
            }

            public void setCM00000230(String CM00000230) {
                this.CM00000230 = CM00000230;
            }

            public String getCM00000235() {
                return CM00000235;
            }

            public void setCM00000235(String CM00000235) {
                this.CM00000235 = CM00000235;
            }
        }

        public static class CtnrTypeBean implements Serializable{
            /**
             * ctnrType : IF1040
             * fullBox : 0
             * emptyBox : 182
             * boxSize : 182
             */

            private String ctnrType;
            private int fullBox;
            private int emptyBox;
            private int boxSize;

            public String getCtnrType() {
                return ctnrType;
            }

            public void setCtnrType(String ctnrType) {
                this.ctnrType = ctnrType;
            }

            public int getFullBox() {
                return fullBox;
            }

            public void setFullBox(int fullBox) {
                this.fullBox = fullBox;
            }

            public int getEmptyBox() {
                return emptyBox;
            }

            public void setEmptyBox(int emptyBox) {
                this.emptyBox = emptyBox;
            }

            public int getBoxSize() {
                return boxSize;
            }

            public void setBoxSize(int boxSize) {
                this.boxSize = boxSize;
            }
        }
    }
}
