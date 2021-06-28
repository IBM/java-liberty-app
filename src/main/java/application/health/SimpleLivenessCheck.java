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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Path;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Liveness
@ApplicationScoped
@Path("health")
public class SimpleLivenessCheck implements HealthCheck {

  @Override
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Get health of application")
  @APIResponses(
          value = {
                  @APIResponse(
                          responseCode = "200",
                          description = "Json indicating health of application",
                          content = @Content(mediaType = "application/json"))
          }
  )
  public HealthCheckResponse call() {

    // if not live logic
    // return HealthCheckResponse.down(this.getClass().getSimpleName());
    return HealthCheckResponse
            .up(this.getClass().getSimpleName());
  }
}