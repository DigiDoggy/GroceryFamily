package GroceryFamily.GroceryMom;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {GroceryFamily.GroceryElders.Config.class})
public class Config {}