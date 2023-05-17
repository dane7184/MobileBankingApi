package co.istad.mobileBanking.api.user.validator.role;

import co.istad.mobileBanking.api.user.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReconstraintValidator implements ConstraintValidator<RoleIdConstraint, List<Integer>> {

    private final UserMapper userMapper;

    /**
     *use loop for find role if have role it can found and return true if not found return false
     * @param roleIds object to validate
     * @param context context in which the constraint is evaluated
     *
     * @return
     */
    @Override
    public boolean isValid(List<Integer> roleIds, ConstraintValidatorContext context) {

        for (Integer role : roleIds){
            if (!userMapper.existByRole(role)){
                return false;
            }
        }
        return true;
    }
}
