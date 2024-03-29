/*
 * Forge Mod Loader
 * Copyright (c) 2012-2013 cpw.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * Contributors:
 *     cpw - implementation
 */

package net.minecraftforge.fml.client;

import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraftforge.fml.common.MissingModsException;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;

public class GuiModsMissing extends GuiErrorScreen
{

    private MissingModsException modsMissing;

    public GuiModsMissing(MissingModsException modsMissing)
    {
        super(null,null);
        this.modsMissing = modsMissing;
    }

    @Override
    public void func_73866_w_()
    {
        super.func_73866_w_();
        this.field_146292_n.clear();
    }
    @Override
    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.func_146276_q_();
        int offset = Math.max(85 - modsMissing.missingMods.size() * 10, 10);
        this.func_73732_a(this.field_146289_q, "Forge Mod Loader has found a problem with your minecraft installation", this.field_146294_l / 2, offset, 0xFFFFFF);
        offset+=10;
        this.func_73732_a(this.field_146289_q, "The mods and versions listed below could not be found", this.field_146294_l / 2, offset, 0xFFFFFF);
        offset+=5;
        for (ArtifactVersion v : modsMissing.missingMods)
        {
            offset+=10;
            if (v instanceof DefaultArtifactVersion)
            {
                DefaultArtifactVersion dav =  (DefaultArtifactVersion)v;
                if (dav.getRange() != null && dav.getRange().isUnboundedAbove())
                {
                    this.func_73732_a(this.field_146289_q, String.format("%s : minimum version required is %s", v.getLabel(), dav.getRange().getLowerBoundString()), this.field_146294_l / 2, offset, 0xEEEEEE);
                    continue;
                }
            }
            this.func_73732_a(this.field_146289_q, String.format("%s : %s", v.getLabel(), v.getRangeString()), this.field_146294_l / 2, offset, 0xEEEEEE);
        }
        offset+=20;
        this.func_73732_a(this.field_146289_q, "The file 'ForgeModLoader-client-0.log' contains more information", this.field_146294_l / 2, offset, 0xFFFFFF);
    }
}
