package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Report;
//タイトルと内容が画面に入力されているか確認
public class ReportValidator {
    public static List<String> validate(Report r){
        List<String> errors = new ArrayList<String>();

        String title_error = _validateTitle(r.getTitle());
        if(!title_error.equals("")){
            errors.add(title_error);
        }

        String started_work_at_error = _validateStarted_work_at(r.getStarted_work_at());
        if(!started_work_at_error.equals("")){
            errors.add(started_work_at_error);
        }

        String finished_work_at_error = _validateFinished_work_at(r.getFinished_work_at());
        if(!finished_work_at_error.equals("")){
            errors.add(finished_work_at_error);
        }

        String content_error = _validateContent(r.getContent());
        if(!content_error.equals("")){
            errors.add(content_error);
        }

        return errors;
    }

    private static String _validateTitle(String title){
        if(title == null || title.equals("")) {
            return "タイトルを入力してください。";
            }

        return "";
    }

    private static String _validateStarted_work_at(String started_work_at){
        if(started_work_at == null || started_work_at.equals("")) {
            return "出勤時間を入力してください。";
            }

        return "";
    }

    private static String _validateFinished_work_at(String finished_work_at){
        if(finished_work_at == null || finished_work_at.equals("")) {
            return "退勤時間を入力してください。";
            }

        return "";
    }

    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "内容を入力してください。";
            }

        return "";
    }
}
