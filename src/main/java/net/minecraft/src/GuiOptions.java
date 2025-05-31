package net.minecraft.src;

public class GuiOptions extends GuiScreen {
	private GuiScreen parentScreen;
	protected String screenTitle = "Options";
	private GameSettings options;

	public GuiOptions(GuiScreen var1, GameSettings var2) {
		this.parentScreen = var1;
		this.options = var2;
	}

	public void initGui() {
		for (int var1 = 0; var1 < this.options.numberOfOptions; ++var1) {
			this.controlList.add(new GuiSmallButton(var1, this.width / 2 - 155 + var1 % 2 * 160, this.height / 6 + 24 * (var1 >> 1), this.options.getOptionDisplayString(var1)));
		}

		this.controlList.add(new GuiButton(100, this.width / 2 - 100, this.height / 6 + 130 + 12, "Controls..."));
		this.controlList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, "Done"));
	}

	protected void actionPerformed(GuiButton button) {
		if(button.enabled) {
			if(button.id < 100) {
				this.options.setOptionValue(button.id, 1);

				for (int i = 0; i < this.controlList.size(); i++) {
					GuiButton b = (GuiButton)this.controlList.get(i);
					if (b.id == button.id) {
						b.displayString = this.options.getOptionDisplayString(button.id);
						break;
					}
				}
			}

			if(button.id == 100) {
				this.mc.displayGuiScreen(new GuiControls(this, this.options));
			}

			if(button.id == 200) {
				this.mc.displayGuiScreen(this.parentScreen);
			}
		}
	}


	public void drawScreen(int var1, int var2, float var3) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 20, 16777215);
		super.drawScreen(var1, var2, var3);
	}
}
