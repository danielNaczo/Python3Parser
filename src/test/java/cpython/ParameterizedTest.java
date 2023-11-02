package cpython;

import basic.BasicTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParameterizedTest extends BasicTest {

    public static String CPYTHON_DIRECTORY_PATH = "examples/cpython/";

    public ParameterizedTest(String testFileName) {
        this.testFileName = testFileName;
        this.pathPrefix = CPYTHON_DIRECTORY_PATH;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        Collection<Object[]> fileNames = new ArrayList<Object[]>();

        File cpythonDirectory = new File(CPYTHON_DIRECTORY_PATH);
        File[] dirListing = cpythonDirectory.listFiles();
        if (dirListing != null) {
            for (File child : dirListing) {
                fileNames.add(generateObjectArray(child.getName()));
            }
        }

        return fileNames;
    }

    private static Object[] generateObjectArray(String fileName) {
        return new Object[]{fileName};
    }

    @Override
    public void setTestFileName() {
        //is set in constructor
    }

    @Override
    public void printInConsole() {
        //showInConsole = true;
    }

}
