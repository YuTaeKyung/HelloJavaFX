package teamproject.taekung.file;

import javafx.collections.ObservableList;
import teamproject.taekung.model.BookModel;
import teamproject.taekung.model.MemberModel;
import teamproject.taekung.model.RentModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by taeku on 2016-09-21.
 */
public class SaveData {


    public static void saveMember(List<MemberModel> ml){
        String fpath ="C:/memberData.dat";

        FileWriter fw = null;
        BufferedWriter bw = null;
        MemberModel mm = null;


        try {
            fw = new FileWriter(fpath);
            bw = new BufferedWriter(fw);

            bw.write("회원번호/이름/전화번호/핸드폰번호/생년월일/주소/이메일\n");

            for(int i = 0; i<ml.size();i++) {
                fw = new FileWriter(fpath,true);
                mm = ml.get(i);

                String dat = "\n" + mm.getMno() + "/" + mm.getName() + "/" + mm.getPhone() + "/" + mm.getCellphone() + "/" + mm.getBirthdate() + "/" + mm.getAddr()
                        + "/" + mm.getEmail() + "\n";
                bw.write(dat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bw != null){try{bw.close(); bw=null;} catch (Exception ex){}}
            if(fw != null){try{fw.close(); fw=null;} catch (Exception ex){}}
        }
    }


    public static void saveBook(List<BookModel> bl){
//        String fpath = "C:/java/java1/1608i/HelloJavaFX-/src/main/java/teamproject/taekung/file/projectData/bookData.dat";
        String fpath = "C:/bookData.dat";
        FileWriter fw = null;
        BufferedWriter bw = null;
        BookModel bm = null;


        try {
            fw = new FileWriter(fpath);
            bw = new BufferedWriter(fw);

            bw.write("도서번호/도서명/장르/저자/출판사/이미지파일명\n");

            for(int i = 0; i<bl.size();i++) {
                fw = new FileWriter(fpath,true);
                bm = bl.get(i);

                String dat = "\n" + bm.getBno() + "/" + bm.getBname() + "/" + bm.getGenre() +
                        "/" + bm.getAuthor() + "/" + bm.getPublisher() + "/" + bm.getBimg()
                         + "\n";
                bw.write(dat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bw != null){try{bw.close(); bw=null;} catch (Exception ex){}}
            if(fw != null){try{fw.close(); fw=null;} catch (Exception ex){}}
        }
    }

    public static void saveRent(ObservableList<RentModel> rl) {
        String fpath = "C:/rentData.dat";
        FileWriter fw = null;
        BufferedWriter bw = null;
        RentModel rm = null;


        try {
            fw = new FileWriter(fpath);
            bw = new BufferedWriter(fw);

            bw.write("대여번호/회원번호/회원이름/도서번호/도서명/대여일/반납일\n");

            for(int i = 0; i<rl.size();i++) {
                fw = new FileWriter(fpath,true);
                rm = rl.get(i);

                String dat = "\n" + rm.getRno() + "/" + rm.getMno() + "/" + rm.getName() +
                        "/" + rm.getBno() + "/" + rm.getBname() + "/" + rm.getRegdate() + rm.getDuedate()
                        + "\n";
                bw.write(dat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bw != null){try{bw.close(); bw=null;} catch (Exception ex){}}
            if(fw != null){try{fw.close(); fw=null;} catch (Exception ex){}}
        }
    }
}
