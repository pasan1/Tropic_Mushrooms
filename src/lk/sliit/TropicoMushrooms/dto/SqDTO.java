package lk.sliit.TropicoMushrooms.dto;

public class SqDTO {
    private int sqNo;
    private String question;

    public SqDTO() {
    }

    public SqDTO(int sqNo, String question) {
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
        return "SqDTO{" +
                "sqNo=" + sqNo +
                ", question='" + question + '\'' +
                '}';
    }
}
