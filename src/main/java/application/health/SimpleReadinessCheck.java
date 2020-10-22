/*******************************************************************************
 * Copyright (c) 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package application.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Readiness
@ApplicationScoped
public class SimpleReadinessCheck implements HealthCheck {

  @Override
  public HealthCheckResponse call() {

    // if not ready logic
    // return HealthCheckResponse.down(this.getClass().getSimpleName());
    return HealthCheckResponse
            .up(this.getClass().getSimpleName());
  }
}