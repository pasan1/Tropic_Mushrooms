package lk.sliit.TropicoMushrooms.dao.custom;

import lk.sliit.TropicoMushrooms.dao.CrudDAO;
import lk.sliit.TropicoMushrooms.entity.SQ;

public interface SqDAO extends CrudDAO<SQ, String> {
    public String getQuestionFromNumber(int no) throws Exception;

    public int getQuestionNumberFromQuestion(String question) throws Exception;
}
