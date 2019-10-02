/**
 *
 * @author Ali Karimizandi
 * @since 2009
 *
 */

package org.zcoreframework.base.http;

import org.springframework.http.ResponseEntity;

public interface HttpPattern {

    void initialize(HttpTemplate template);

    void finalize(HttpTemplate template, ResponseEntity<?> entity);

}
