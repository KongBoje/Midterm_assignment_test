/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.javaanpr.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.xml.sax.SAXException;

/**
 *
 * @author Micha
 */
@RunWith(value = Parameterized.class)
public class RecognitionAllIt {
    
    private static Properties props;
    private static File[] snapshots;
    private static Intelligence intel;
    private final static Logger logger = Logger.getLogger(RecognitionAllIt.class.getName());

    public static void setUp() throws FileNotFoundException, IOException, ParserConfigurationException, SAXException {

        System.out.println("################ KAN JEG SE BEFORE=???");
        intel = new Intelligence();

        String snapshotDirPath = "src/test/resources/snapshots";
        String resultsPath = "src/test/resources/results.properties";
        try (InputStream resultsStream = new FileInputStream(new File(resultsPath))) {
            props = new Properties();
            props.load(resultsStream);
            //assertTrue(props.size() > 0);
        }

        File snapshotDir = new File(snapshotDirPath);
        snapshots = snapshotDir.listFiles();
        logger.log(Level.SEVERE, "################## This is my size{0}", snapshots.length);
        assertNotNull(snapshots);
    }

    //default value = 0
    @Parameter(value = 0)
    public String data;

    @Parameters
    public static List<String> data() throws IllegalArgumentException, IllegalArgumentException, FileNotFoundException, IOException, ParserConfigurationException, SAXException {
        
        setUp();
        
        List<String> params = new ArrayList();
        logger.log(Level.SEVERE, "###################  Min snapshots er: {0}", Arrays.toString(snapshots));
        try {
            for (File snap : snapshots) {
                CarSnapshot carSnap = new CarSnapshot(new FileInputStream(snap));
                assertNotNull("carSnap is null", carSnap);
                assertNotNull("carSnap.image is null", carSnap.getImage());
                String snapName = snap.getName();
                carSnap.close();
                params.add(intel.recognize(carSnap, false) + "," + props.getProperty(snapName));
//            hm.put(intel.recognize(carSnap, false), props.getProperty(snapName));
            }
        } catch (IOException ex) {
            // DO stuff
        }
        return params;
    }

    @Test
    public void test_addTwoNumbes() {
        
        List<String> tempData = Arrays.asList(data.split("\\s*,\\s*"));
        assertThat(tempData.get(1), is(tempData.get(0)));
    }
}
