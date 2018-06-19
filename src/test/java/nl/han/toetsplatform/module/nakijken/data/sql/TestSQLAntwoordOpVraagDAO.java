package nl.han.toetsplatform.module.nakijken.data.sql;
import nl.han.toetsplatform.module.nakijken.data.data.SQLLoader;
import nl.han.toetsplatform.module.nakijken.data.data.dto_model_mapper.createDatabase;
import nl.han.toetsplatform.module.nakijken.data.data.sql.SQLAntwoordOpVraagDAO;
import nl.han.toetsplatform.module.nakijken.model.AntwoordOpVraag;
import nl.han.toetsplatform.module.shared.storage.StorageDao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestSQLAntwoordOpVraagDAO {

    SQLAntwoordOpVraagDAO sqlAntwoordOpVraagDAO;
    createDatabase SqlDataBaseCreator;

    AntwoordOpVraag uitgevoerdVraag = new AntwoordOpVraag();

    @Before
    public void init(){
        SQLLoader sqlLoader = new SQLLoader();
        StorageDao storageDao = new TestStorageDao();
        SqlDataBaseCreator  = new createDatabase(storageDao, sqlLoader);
        SqlDataBaseCreator.createDatabsase();

        sqlAntwoordOpVraagDAO = new SQLAntwoordOpVraagDAO(storageDao, sqlLoader);
        uitgevoerdVraag.setVraagId("5e28d892-aa5e-4850-8849-9445fe54a8f2");
        uitgevoerdVraag.setVraagVersie("1");
        uitgevoerdVraag.setStudentNummer(563029);
        uitgevoerdVraag.setTentamenCode("b135101b-01cb-4340-9d70-7383fa7faecf");
        uitgevoerdVraag.setTentamenVersie("1");
        uitgevoerdVraag.setAntwoord("blije koe in de wei");
        uitgevoerdVraag.setBehaaldePunten(9);
        uitgevoerdVraag.setNakijkComment("MENSNE ZIJN INCOMPETENT");
    }

    @Test
    public void testSelectAndInsert(){
        List<AntwoordOpVraag> beantwoordeVragen1 = sqlAntwoordOpVraagDAO.loadAllAntwoorden();
        sqlAntwoordOpVraagDAO.saveAntwoorden(uitgevoerdVraag);
        List<AntwoordOpVraag> beantwoordeVragen2 = sqlAntwoordOpVraagDAO.loadAllAntwoorden();
        Assert.assertEquals(beantwoordeVragen1.size() + 1, beantwoordeVragen2.size());
    }

    @Test
    public void testUpdate(){
        List<AntwoordOpVraag> beantwoordeVragen1 = sqlAntwoordOpVraagDAO.loadAllAntwoorden();
        AntwoordOpVraag test1 = new AntwoordOpVraag();

        for(AntwoordOpVraag getData: beantwoordeVragen1){
            test1 = getData;
        }

        AntwoordOpVraag updatingVraag = new AntwoordOpVraag();
        updatingVraag.setVraagId("5e28d892-aa5e-4850-8849-9445fe54a8f2");
        updatingVraag.setVraagVersie("1");
        updatingVraag.setStudentNummer(563029);
        updatingVraag.setTentamenCode("b135101b-01cb-4340-9d70-7383fa7faecf");
        updatingVraag.setTentamenVersie("1");
        updatingVraag.setAntwoord("blije koe in de wei");
        updatingVraag.setBehaaldePunten(9);
        updatingVraag.setNakijkComment("this is not a comment");
        sqlAntwoordOpVraagDAO.updateAntwoord(updatingVraag);

        List<AntwoordOpVraag> beantwoordeVragen2 = sqlAntwoordOpVraagDAO.loadAllAntwoorden();
        AntwoordOpVraag test2 = new AntwoordOpVraag();

        for(AntwoordOpVraag getData: beantwoordeVragen2){
            test2 = getData;
        }

        Assert.assertEquals(test1.getNakijkComment(),test2.getNakijkComment());

    }

    @Test
    public void testSelectFilter() {
        AntwoordOpVraag testVraag = new AntwoordOpVraag();
        testVraag.setVraagId("1f23203f-68b4-4f67-8970-492556f77ab1");
        testVraag.setVraagVersie("1");
        testVraag.setStudentNummer(569074);
        testVraag.setTentamenCode("1cefc7cb-1154-4995-a0ae-1565f559cc89");
        testVraag.setTentamenVersie("1");
        testVraag.setAntwoord("om aan de over kant te komen");
        testVraag.setBehaaldePunten(9);
        testVraag.setNakijkComment("To be implemented");
        sqlAntwoordOpVraagDAO.saveAntwoorden(testVraag);
        List<AntwoordOpVraag> gefilterdeVragen =  sqlAntwoordOpVraagDAO.loadAntwoorden("1f23203f-68b4-4f67-8970-492556f77ab1", "1", "1cefc7cb-1154-4995-a0ae-1565f559cc89", "1");
        AntwoordOpVraag geslecteerdeVraag = new AntwoordOpVraag();

        for(AntwoordOpVraag select : gefilterdeVragen){
            geslecteerdeVraag = select;
        }

        Assert.assertEquals(geslecteerdeVraag.getVraagId(), testVraag.getVraagId());
        Assert.assertEquals(geslecteerdeVraag.getTentamenCode(), testVraag.getTentamenCode());
    }



    @Test
    public void testSelectWrong(){
        List<AntwoordOpVraag> beantwoordenVraag = sqlAntwoordOpVraagDAO.loadAllAntwoorden();
        Assert.assertNotEquals(99 , beantwoordenVraag.size());
    }
}
