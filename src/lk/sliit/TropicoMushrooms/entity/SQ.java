package lk.sliit.TropicoMushrooms.entity;

public class SQ implements SuperEntity {
    private int sqNo;
    private String question;

    public SQ() {
    }

    public SQ(int sqNo, String question) {
        this.setSqNo(sqNo);
        this.setQuestion(question);
    }

    public int getSqNo() {
        return sqNo;
    }

    public void setSqNo(int sqNo) {
        this.sqNo = sqNo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "SQ{" +
                "sqNo=" + sqNo +
                ", question='" + question + '\'' +
                '}';
    }
}
