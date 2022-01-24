package com.withertech.mixin.forge;

import com.withertech.api.IMFStorage;
import net.minecraftforge.energy.IEnergyStorage;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IEnergyStorage.class)
public interface IEnergyStorageMixin extends IMFStorage
{

}
