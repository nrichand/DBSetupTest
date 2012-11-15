package org.springframework.samples.petclinic.dbsetup;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;

import com.ninja_squad.dbsetup.operation.Operation;

public class CommonOperations {

	public static final Operation DELETE_ALL = deleteAllFrom("specialties", "owners", "pets", "types", "visits");
}
