package org.dreamkite.pg;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest(classes={UsePgApplication.class})
@Profile(value="testPg")
public class TestPgDao {
}
