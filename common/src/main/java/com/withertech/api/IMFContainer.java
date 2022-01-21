package com.withertech.api;

import java.util.Optional;

public interface IMFContainer
{
	Optional<IMFStorage> getStorageFor(Object that);
}
