package org.uaf.cd_web.services;
import org.springframework.stereotype.Service;
import org.uaf.cd_web.entity.Love;
import org.uaf.cd_web.reponsitory.LoveReponesitory;
import org.uaf.cd_web.services.IServices.ILoveService;
import java.util.List;


@Service
public class LoveServiceImp implements ILoveService {
    private final LoveReponesitory loveReponesitory;

    @Override
    public long getCount() {
        return loveReponesitory.count();
    }

    public LoveServiceImp(LoveReponesitory loveReponesitory) {
        this.loveReponesitory = loveReponesitory;
    }
    @Override
    public  void insertLove(String idUser, String idPr) {
        Love l= new Love();
        l.setId((int) (getCount()+1));
        l.setIdUser(idUser);
        l.setIdPr(idPr);
        loveReponesitory.save(l);
    }
    @Override
    public boolean checkLiked(String idUser, String idPr){
        List<Love> listLiked = loveReponesitory.checkLiked(idUser,idPr);
        return !listLiked.isEmpty();
    }


}
