import com.smallworld.PropertiesLoader;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PropertiesLoaderTest {

    /**
     * For loading the configuration file.
     */
    private static PropertiesLoader propertiesLoader;

    /**
     * Setups the {@code propertiesLoader} object.
     * @throws ConfigurationException
     */
    @BeforeAll
    public static void setUp() throws ConfigurationException {
        propertiesLoader = new PropertiesLoader();
    }

    /**
     * Tests if the file path matches the one mentioned in application.properties.
     * @throws IOException - when json file path is missing.
     */
    @Test
    public void testGetJsonFilePath() throws IOException {
        String expectedFilePath = "transactions.json";
        String actualFilePath = propertiesLoader.getJsonFilePath();
        Assertions.assertEquals(expectedFilePath, actualFilePath);
    }

}
