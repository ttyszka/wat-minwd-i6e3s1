import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ojalgo.optimisation.Variable;


/**
 * Pojo abstract class to hold p1 and p2 variable as atributes
 */
@NoArgsConstructor
@Getter
@Setter
public abstract class ModelVariable {

    //

    protected Variable p1;
    protected Variable p2;


}
