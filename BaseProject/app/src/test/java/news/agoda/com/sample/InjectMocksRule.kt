package news.agoda.com.sample

import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations


/**
 * @author linhtruong
 */
class InjectMocksRule {

    companion object {
        fun create(testClass: Any) = TestRule {statement, _ ->
            MockitoAnnotations.initMocks(testClass)
            statement
        }
    }
}