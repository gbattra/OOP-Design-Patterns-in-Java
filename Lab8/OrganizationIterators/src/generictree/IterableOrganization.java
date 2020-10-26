package generictree;

import util.Organization;

/**
 * Interface for iterable organizations.
 */
public interface IterableOrganization extends Organization, Iterable<Employee> {
}
