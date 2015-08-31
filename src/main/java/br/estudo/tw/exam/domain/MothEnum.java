package br.estudo.tw.exam.domain;

/**
 * Created by torugo on 30/08/15.
 */
public enum MothEnum {
    MARCH("Mar");

    private String moth;

    MothEnum(String moth) {
        this.moth = moth;
    }

    public String getMoth() {
        return moth;
    }

    public static MothEnum getMothByString(String strMoth) {
        for (MothEnum moth : MothEnum.values()) {
            if (moth.getMoth().equalsIgnoreCase(strMoth)) {
                return moth;
            }
        }
        return null;
    }
}
