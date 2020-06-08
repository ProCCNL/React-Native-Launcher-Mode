import { NativeModules } from 'react-native';

type LauncherModeType = {
  resetPreferredLauncherAndOpenChooser(): Promise<boolean>;
  isPreferredLauncher(): Promise<boolean>;
};

const { LauncherMode } = NativeModules;

export default LauncherMode as LauncherModeType;
