package com.tinkerpop.blueprints.pgm.impls.tg;

import com.tinkerpop.blueprints.pgm.AutomaticIndexTestSuite;
import com.tinkerpop.blueprints.pgm.EdgeTestSuite;
import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.GraphTestSuite;
import com.tinkerpop.blueprints.pgm.IndexTestSuite;
import com.tinkerpop.blueprints.pgm.IndexableGraphTestSuite;
import com.tinkerpop.blueprints.pgm.TestSuite;
import com.tinkerpop.blueprints.pgm.VertexTestSuite;
import com.tinkerpop.blueprints.pgm.impls.GraphTest;
import com.tinkerpop.blueprints.pgm.util.io.gml.GMLReaderTestSuite;
import com.tinkerpop.blueprints.pgm.util.io.graphml.GraphMLReaderTestSuite;
import com.tinkerpop.blueprints.pgm.util.io.graphson.GraphSONReaderTestSuite;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class TinkerGraphTest extends GraphTest {

    public TinkerGraphTest() {
        allowsDuplicateEdges = true;
        allowsSelfLoops = true;
        ignoresSuppliedIds = false;
        isPersistent = true;
        isRDFModel = false;
        supportsVertexIteration = true;
        supportsEdgeIteration = true;
        supportsVertexIndex = true;
        supportsEdgeIndex = true;
        supportsTransactions = false;

        allowSerializableObjectProperty = true;
        allowBooleanProperty = true;
        allowDoubleProperty = true;
        allowFloatProperty = true;
        allowIntegerProperty = true;
        allowPrimitiveArrayProperty = true;
        allowListProperty = true;
        allowLongProperty = true;
        allowMapProperty = true;
        allowStringProperty = true;
    }

    /*
      * public void testTinkerBenchmarkTestSuite() throws Exception { this.stopWatch(); doTestSuite(new
      * TinkerBenchmarkTestSuite(this)); printTestPerformance("TinkerBenchmarkTestSuite", this.stopWatch()); }
      */

    public void testVertexTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new VertexTestSuite(this));
        printTestPerformance("VertexTestSuite", this.stopWatch());
    }

    public void testEdgeTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new EdgeTestSuite(this));
        printTestPerformance("EdgeTestSuite", this.stopWatch());
    }

    public void testGraphTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new GraphTestSuite(this));
        printTestPerformance("GraphTestSuite", this.stopWatch());
    }

    public void testIndexableGraphTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new IndexableGraphTestSuite(this));
        printTestPerformance("IndexableGraphTestSuite", this.stopWatch());
    }

    public void testIndexTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new IndexTestSuite(this));
        printTestPerformance("IndexTestSuite", this.stopWatch());
    }

    public void testAutomaticIndexTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new AutomaticIndexTestSuite(this));
        printTestPerformance("AutomaticIndexTestSuite", this.stopWatch());
    }

    public void testGraphMLReaderTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new GraphMLReaderTestSuite(this));
        printTestPerformance("GraphMLReaderTestSuite", this.stopWatch());
    }

    public void testGraphSONReaderTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new GraphSONReaderTestSuite(this));
        printTestPerformance("GraphSONReaderTestSuite", this.stopWatch());
    }

    public void testGMLReaderTestSuite() throws Exception {
        this.stopWatch();
        doTestSuite(new GMLReaderTestSuite(this));
        printTestPerformance("GMLReaderTestSuite", this.stopWatch());
    }

    @Override
    public Graph getGraphInstance() {
        String directory = System.getProperty("tinkerGraphDirectory");
        if (directory == null) {
            directory = this.getWorkingDirectory();
        }
        return new TinkerGraph(directory);
    }

    private String getWorkingDirectory() {
        String directory = System.getProperty("tinkerGraphDirectory");
        if (directory == null) {
            if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
                directory = "C:/temp/blueprints_test";
            } else {
                directory = "/tmp/blueprints_test";
            }
        }
        return directory;
    }

    @Override
    public void doTestSuite(final TestSuite testSuite) throws Exception {
        String doTest = System.getProperty("testTinkerGraph");
        if (doTest == null || doTest.equals("true")) {
            String directory = System.getProperty("tinkerGraphDirectory");
            if (directory == null) {
                directory = this.getWorkingDirectory();
            }
            deleteDirectory(new File(directory));
            for (Method method : testSuite.getClass().getDeclaredMethods()) {
                if (method.getName().startsWith("test")) {
                    System.out.println("Testing " + method.getName() + "...");
                    method.invoke(testSuite);
                    deleteDirectory(new File(directory));
                }
            }
        }
    }
}
