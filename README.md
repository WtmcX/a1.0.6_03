# Alpha 1.0.6_03
TeaVM port of Minecraft Alpha 1.0.6_03 using u50 classes from EaglercraftX

Alpha minecraft uses a 5-world save scheme, so you're limited to a maximum of 5 worlds at any time in singleplayer.

### Download locally: [Singleplayer_Offline_Download_Version.html](javascript/EaglercraftX_1.8_Offline_en_US.html)

## Compiling

Just import this entire repository as a gradle project and run `CompileJS.sh`. It will generate a `classes.js` in the `javascript` folder of this repository.

To change any textures and stuff, make your changes in `lwjgl-rundir/resources` and then run `CompileEPK.sh` to generate a new `assets.epk` in the `javascript` folder where `classes.js` is. Copy the new `javascript/assets.epk` onto your website over the old one to update it.

**Make sure you install java and add it to your PATH, or these scripts will not work.**

(Readme Skidded from catfoolyou)