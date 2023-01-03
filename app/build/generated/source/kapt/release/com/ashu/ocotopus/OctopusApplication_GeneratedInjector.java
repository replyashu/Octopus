package com.ashu.ocotopus;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = OctopusApplication.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface OctopusApplication_GeneratedInjector {
  void injectOctopusApplication(OctopusApplication octopusApplication);
}
