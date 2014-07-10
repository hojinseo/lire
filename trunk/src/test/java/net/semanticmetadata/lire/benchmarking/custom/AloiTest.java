/*
 * This file is part of the LIRE project: http://www.semanticmetadata.net/lire
 * LIRE is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * LIRE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LIRE; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * We kindly ask you to refer the any or one of the following publications in
 * any publication mentioning or employing Lire:
 *
 * Lux Mathias, Savvas A. Chatzichristofis. Lire: Lucene Image Retrieval –
 * An Extensible Java CBIR Library. In proceedings of the 16th ACM International
 * Conference on Multimedia, pp. 1085-1088, Vancouver, Canada, 2008
 * URL: http://doi.acm.org/10.1145/1459359.1459577
 *
 * Lux Mathias. Content Based Image Retrieval with LIRE. In proceedings of the
 * 19th ACM International Conference on Multimedia, pp. 735-738, Scottsdale,
 * Arizona, USA, 2011
 * URL: http://dl.acm.org/citation.cfm?id=2072432
 *
 * Mathias Lux, Oge Marques. Visual Information Retrieval using Java and LIRE
 * Morgan & Claypool, 2013
 * URL: http://www.morganclaypool.com/doi/abs/10.2200/S00468ED1V01Y201301ICR025
 *
 * Copyright statement:
 * --------------------
 * (c) 2002-2013 by Mathias Lux (mathias@juggle.at)
 *     http://www.semanticmetadata.net/lire, http://www.lire-project.net
 */

package net.semanticmetadata.lire.benchmarking.custom;

import junit.framework.TestCase;
import net.semanticmetadata.lire.DocumentBuilder;
import net.semanticmetadata.lire.ImageSearchHits;
import net.semanticmetadata.lire.imageanalysis.*;
import net.semanticmetadata.lire.impl.ChainedDocumentBuilder;
import net.semanticmetadata.lire.impl.GenericDocumentBuilder;
import net.semanticmetadata.lire.impl.GenericFastImageSearcher;
import net.semanticmetadata.lire.indexing.parallel.ParallelIndexer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.MMapDirectory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by mlux on 10.07.14.   A test done with Jakub Lokoc, lokoc@ksi.ms.mff.cuni.cz
 */
public class AloiTest extends TestCase {
    ParallelIndexer parallelIndexer;
    private String indexPath = "aloi-index";
    private String testExtensive = "./testdata/Aloi/png";
    private ChainedDocumentBuilder builder;
    String[] queries = new String[]{
            "12",
            "24",
            "36",
            "48",
            "60",
            "72",
            "84",
            "96",
            "108",
            "120",
            "132",
            "144",
            "156",
            "168",
            "180",
            "192",
            "204",
            "216",
            "228",
            "240",
            "252",
            "264",
            "276",
            "288",
            "300",
            "312",
            "324",
            "336",
            "348",
            "360",
            "372",
            "384",
            "396",
            "408",
            "420",
            "432",
            "444",
            "456",
            "468",
            "480",
            "492",
            "504",
            "516",
            "528",
            "540",
            "552",
            "564",
            "576",
            "588",
            "600",
            "612",
            "624",
            "636",
            "648",
            "660",
            "672",
            "684",
            "696",
            "708",
            "720",
            "732",
            "744",
            "756",
            "768",
            "780",
            "792",
            "804",
            "816",
            "828",
            "840",
            "852",
            "864",
            "876",
            "888",
            "900",
            "912",
            "924",
            "936",
            "948",
            "960",
            "972",
            "984",
            "996",
            "1008",
            "1020",
            "1032",
            "1044",
            "1056",
            "1068",
            "1080",
            "1092",
            "1104",
            "1116",
            "1128",
            "1140",
            "1152",
            "1164",
            "1176",
            "1188",
            "1200",
            "1212",
            "1224",
            "1236",
            "1248",
            "1260",
            "1272",
            "1284",
            "1296",
            "1308",
            "1320",
            "1332",
            "1344",
            "1356",
            "1368",
            "1380",
            "1392",
            "1404",
            "1416",
            "1428",
            "1440",
            "1452",
            "1464",
            "1476",
            "1488",
            "1500",
            "1512",
            "1524",
            "1536",
            "1548",
            "1560",
            "1572",
            "1584",
            "1596",
            "1608",
            "1620",
            "1632",
            "1644",
            "1656",
            "1668",
            "1680",
            "1692",
            "1704",
            "1716",
            "1728",
            "1740",
            "1752",
            "1764",
            "1776",
            "1788",
            "1800",
            "1812",
            "1824",
            "1836",
            "1848",
            "1860",
            "1872",
            "1884",
            "1896",
            "1908",
            "1920",
            "1932",
            "1944",
            "1956",
            "1968",
            "1980",
            "1992",
            "2004",
            "2016",
            "2028",
            "2040",
            "2052",
            "2064",
            "2076",
            "2088",
            "2100",
            "2112",
            "2124",
            "2136",
            "2148",
            "2160",
            "2172",
            "2184",
            "2196",
            "2208",
            "2220",
            "2232",
            "2244",
            "2256",
            "2268",
            "2280",
            "2292",
            "2304",
            "2316",
            "2328",
            "2340",
            "2352",
            "2364",
            "2376",
            "2388",
            "2400",
            "2412",
            "2424",
            "2436",
            "2448",
            "2460",
            "2472",
            "2484",
            "2496",
            "2508",
            "2520",
            "2532",
            "2544",
            "2556",
            "2568",
            "2580",
            "2592",
            "2604",
            "2616",
            "2628",
            "2640",
            "2652",
            "2664",
            "2676",
            "2688",
            "2700",
            "2712",
            "2724",
            "2736",
            "2748",
            "2760",
            "2772",
            "2784",
            "2796",
            "2808",
            "2820",
            "2832",
            "2844",
            "2856",
            "2868",
            "2880",
            "2892",
            "2904",
            "2916",
            "2928",
            "2940",
            "2952",
            "2964",
            "2976",
            "2988",
            "3000",
            "3012",
            "3024",
            "3036",
            "3048",
            "3060",
            "3072",
            "3084",
            "3096",
            "3108",
            "3120",
            "3132",
            "3144",
            "3156",
            "3168",
            "3180",
            "3192",
            "3204",
            "3216",
            "3228",
            "3240",
            "3252",
            "3264",
            "3276",
            "3288",
            "3300",
            "3312",
            "3324",
            "3336",
            "3348",
            "3360",
            "3372",
            "3384",
            "3396",
            "3408",
            "3420",
            "3432",
            "3444",
            "3456",
            "3468",
            "3480",
            "3492",
            "3504",
            "3516",
            "3528",
            "3540",
            "3552",
            "3564",
            "3576",
            "3588",
            "3600",
            "3612",
            "3624",
            "3636",
            "3648",
            "3660",
            "3672",
            "3684",
            "3696",
            "3708",
            "3720",
            "3732",
            "3744",
            "3756",
            "3768",
            "3780",
            "3792",
            "3804",
            "3816",
            "3828",
            "3840",
            "3852",
            "3864",
            "3876",
            "3888",
            "3900",
            "3912",
            "3924",
            "3936",
            "3948",
            "3960",
            "3972",
            "3984",
            "3996",
            "4008",
            "4020",
            "4032",
            "4044",
            "4056",
            "4068",
            "4080",
            "4092",
            "4104",
            "4116",
            "4128",
            "4140",
            "4152",
            "4164",
            "4176",
            "4188",
            "4200",
            "4212",
            "4224",
            "4236",
            "4248",
            "4260",
            "4272",
            "4284",
            "4296",
            "4308",
            "4320",
            "4332",
            "4344",
            "4356",
            "4368",
            "4380",
            "4392",
            "4404",
            "4416",
            "4428",
            "4440",
            "4452",
            "4464",
            "4476",
            "4488",
            "4500",
            "4512",
            "4524",
            "4536",
            "4548",
            "4560",
            "4572",
            "4584",
            "4596",
            "4608",
            "4620",
            "4632",
            "4644",
            "4656",
            "4668",
            "4680",
            "4692",
            "4704",
            "4716",
            "4728",
            "4740",
            "4752",
            "4764",
            "4776",
            "4788",
            "4800",
            "4812",
            "4824",
            "4836",
            "4848",
            "4860",
            "4872",
            "4884",
            "4896",
            "4908",
            "4920",
            "4932",
            "4944",
            "4956",
            "4968",
            "4980",
            "4992",
            "5004",
            "5016",
            "5028",
            "5040",
            "5052",
            "5064",
            "5076",
            "5088",
            "5100",
            "5112",
            "5124",
            "5136",
            "5148",
            "5160",
            "5172",
            "5184",
            "5196",
            "5208",
            "5220",
            "5232",
            "5244",
            "5256",
            "5268",
            "5280",
            "5292",
            "5304",
            "5316",
            "5328",
            "5340",
            "5352",
            "5364",
            "5376",
            "5388",
            "5400",
            "5412",
            "5424",
            "5436",
            "5448",
            "5460",
            "5472",
            "5484",
            "5496",
            "5508",
            "5520",
            "5532",
            "5544",
            "5556",
            "5568",
            "5580",
            "5592",
            "5604",
            "5616",
            "5628",
            "5640",
            "5652",
            "5664",
            "5676",
            "5688",
            "5700",
            "5712",
            "5724",
            "5736",
            "5748",
            "5760",
            "5772",
            "5784",
            "5796",
            "5808",
            "5820",
            "5832",
            "5844",
            "5856",
            "5868",
            "5880",
            "5892",
            "5904",
            "5916",
            "5928",
            "5940",
            "5952",
            "5964",
            "5976",
            "5988",
            "6000",
            "6012",
            "6024",
            "6036",
            "6048",
            "6060",
            "6072",
            "6084",
            "6096",
            "6108",
            "6120",
            "6132",
            "6144",
            "6156",
            "6168",
            "6180",
            "6192",
            "6204",
            "6216",
            "6228",
            "6240",
            "6252",
            "6264",
            "6276",
            "6288",
            "6300",
            "6312",
            "6324",
            "6336",
            "6348",
            "6360",
            "6372",
            "6384",
            "6396",
            "6408",
            "6420",
            "6432",
            "6444",
            "6456",
            "6468",
            "6480",
            "6492",
            "6504",
            "6516",
            "6528",
            "6540",
            "6552",
            "6564",
            "6576",
            "6588",
            "6600",
            "6612",
            "6624",
            "6636",
            "6648",
            "6660",
            "6672",
            "6684",
            "6696",
            "6708",
            "6720",
            "6732",
            "6744",
            "6756",
            "6768",
            "6780",
            "6792",
            "6804",
            "6816",
            "6828",
            "6840",
            "6852",
            "6864",
            "6876",
            "6888",
            "6900",
            "6912",
            "6924",
            "6936",
            "6948",
            "6960",
            "6972",
            "6984",
            "6996",
            "7008",
            "7020",
            "7032",
            "7044",
            "7056",
            "7068",
            "7080",
            "7092",
            "7104",
            "7116",
            "7128",
            "7140",
            "7152",
            "7164",
            "7176",
            "7188",
            "7200",
            "7212",
            "7224",
            "7236",
            "7248",
            "7260",
            "7272",
            "7284",
            "7296",
            "7308",
            "7320",
            "7332",
            "7344",
            "7356",
            "7368",
            "7380",
            "7392",
            "7404",
            "7416",
            "7428",
            "7440",
            "7452",
            "7464",
            "7476",
            "7488",
            "7500",
            "7512",
            "7524",
            "7536",
            "7548",
            "7560",
            "7572",
            "7584",
            "7596",
            "7608",
            "7620",
            "7632",
            "7644",
            "7656",
            "7668",
            "7680",
            "7692",
            "7704",
            "7716",
            "7728",
            "7740",
            "7752",
            "7764",
            "7776",
            "7788",
            "7800",
            "7812",
            "7824",
            "7836",
            "7848",
            "7860",
            "7872",
            "7884",
            "7896",
            "7908",
            "7920",
            "7932",
            "7944",
            "7956",
            "7968",
            "7980",
            "7992",
            "8004",
            "8016",
            "8028",
            "8040",
            "8052",
            "8064",
            "8076",
            "8088",
            "8100",
            "8112",
            "8124",
            "8136",
            "8148",
            "8160",
            "8172",
            "8184",
            "8196",
            "8208",
            "8220",
            "8232",
            "8244",
            "8256",
            "8268",
            "8280",
            "8292",
            "8304",
            "8316",
            "8328",
            "8340",
            "8352",
            "8364",
            "8376",
            "8388",
            "8400",
            "8412",
            "8424",
            "8436",
            "8448",
            "8460",
            "8472",
            "8484",
            "8496",
            "8508",
            "8520",
            "8532",
            "8544",
            "8556",
            "8568",
            "8580",
            "8592",
            "8604",
            "8616",
            "8628",
            "8640",
            "8652",
            "8664",
            "8676",
            "8688",
            "8700",
            "8712",
            "8724",
            "8736",
            "8748",
            "8760",
            "8772",
            "8784",
            "8796",
            "8808",
            "8820",
            "8832",
            "8844",
            "8856",
            "8868",
            "8880",
            "8892",
            "8904",
            "8916",
            "8928",
            "8940",
            "8952",
            "8964",
            "8976",
            "8988",
            "9000",
            "9012",
            "9024",
            "9036",
            "9048",
            "9060",
            "9072",
            "9084",
            "9096",
            "9108",
            "9120",
            "9132",
            "9144",
            "9156",
            "9168",
            "9180",
            "9192",
            "9204",
            "9216",
            "9228",
            "9240",
            "9252",
            "9264",
            "9276",
            "9288",
            "9300",
            "9312",
            "9324",
            "9336",
            "9348",
            "9360",
            "9372",
            "9384",
            "9396",
            "9408",
            "9420",
            "9432",
            "9444",
            "9456",
            "9468",
            "9480",
            "9492",
            "9504",
            "9516",
            "9528",
            "9540",
            "9552",
            "9564",
            "9576",
            "9588",
            "9600",
            "9612",
            "9624",
            "9636",
            "9648",
            "9660",
            "9672",
            "9684",
            "9696",
            "9708",
            "9720",
            "9732",
            "9744",
            "9756",
            "9768",
            "9780",
            "9792",
            "9804",
            "9816",
            "9828",
            "9840",
            "9852",
            "9864",
            "9876",
            "9888",
            "9900",
            "9912",
            "9924",
            "9936",
            "9948",
            "9960",
            "9972",
            "9984",
            "9996",
            "10008",
            "10020",
            "10032",
            "10044",
            "10056",
            "10068",
            "10080",
            "10092",
            "10104",
            "10116",
            "10128",
            "10140",
            "10152",
            "10164",
            "10176",
            "10188",
            "10200",
            "10212",
            "10224",
            "10236",
            "10248",
            "10260",
            "10272",
            "10284",
            "10296",
            "10308",
            "10320",
            "10332",
            "10344",
            "10356",
            "10368",
            "10380",
            "10392",
            "10404",
            "10416",
            "10428",
            "10440",
            "10452",
            "10464",
            "10476",
            "10488",
            "10500",
            "10512",
            "10524",
            "10536",
            "10548",
            "10560",
            "10572",
            "10584",
            "10596",
            "10608",
            "10620",
            "10632",
            "10644",
            "10656",
            "10668",
            "10680",
            "10692",
            "10704",
            "10716",
            "10728",
            "10740",
            "10752",
            "10764",
            "10776",
            "10788",
            "10800",
            "10812",
            "10824",
            "10836",
            "10848",
            "10860",
            "10872",
            "10884",
            "10896",
            "10908",
            "10920",
            "10932",
            "10944",
            "10956",
            "10968",
            "10980",
            "10992",
            "11004",
            "11016",
            "11028",
            "11040",
            "11052",
            "11064",
            "11076",
            "11088",
            "11100",
            "11112",
            "11124",
            "11136",
            "11148",
            "11160",
            "11172",
            "11184",
            "11196",
            "11208",
            "11220",
            "11232",
            "11244",
            "11256",
            "11268",
            "11280",
            "11292",
            "11304",
            "11316",
            "11328",
            "11340",
            "11352",
            "11364",
            "11376",
            "11388",
            "11400",
            "11412",
            "11424",
            "11436",
            "11448",
            "11460",
            "11472",
            "11484",
            "11496",
            "11508",
            "11520",
            "11532",
            "11544",
            "11556",
            "11568",
            "11580",
            "11592",
            "11604",
            "11616",
            "11628",
            "11640",
            "11652",
            "11664",
            "11676",
            "11688",
            "11700",
            "11712",
            "11724",
            "11736",
            "11748",
            "11760",
            "11772",
            "11784",
            "11796",
            "11808",
            "11820",
            "11832",
            "11844",
            "11856",
            "11868",
            "11880",
            "11892",
            "11904",
            "11916",
            "11928",
            "11940",
            "11952",
            "11964",
            "11976",
            "11988"
    };

    //    private final Class feature = JCD.class;
    private Class[] features = new Class[]{
            JCD.class, CEDD.class, AutoColorCorrelogram.class, PHOG.class, OpponentHistogram.class,
            EdgeHistogram.class, ScalableColor.class, ColorLayout.class, FCTH.class, FuzzyOpponentHistogram.class, JpegCoefficientHistogram.class

    };

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        parallelIndexer = new ParallelIndexer(8, indexPath, testExtensive, true) {
            @Override
            public void addBuilders(ChainedDocumentBuilder builder) {
                for (int i = 0; i < features.length; i++) {
                    builder.addBuilder(new GenericDocumentBuilder(features[i]));
                }

            }
        };
    }

    public void testIndex() {
    }

    public void testSearch() throws IOException {
        parallelIndexer.run();
        for (int f = 0; f < features.length; f++) {
            System.out.println(features[f].getName());
            GenericFastImageSearcher s = new GenericFastImageSearcher(12000, features[f]);
            IndexReader reader = DirectoryReader.open(MMapDirectory.open(new File(indexPath)));
            File out = new File(features[f].getName() + ".csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter(out));
            for (int i = 0; i < 500; i++) {
                if (i%50==0) System.out.print(".");
                String query = queries[i];
                BufferedImage queryImage = ImageIO.read(new File(testExtensive + "/" + query + ".png"));
                ImageSearchHits hits = s.search(queryImage, reader);
                bw.write(query + ", ");
                for (int j = 0; j < hits.length(); j++) {
                    bw.write(hits.doc(j).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0].replace("C:\\Java\\Projects\\LireSVN\\testdata\\Aloi\\png\\", "").replace(".png", ""));
                    bw.write(",");
                }
                bw.write("\n");
            }
            bw.close();
            System.out.println();
        }
    }
}
