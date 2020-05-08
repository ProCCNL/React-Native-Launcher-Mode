import { NativeModules } from 'react-native';

type LauncherModeType = {
  multiply(a: number, b: number): Promise<number>;
};

const { LauncherMode } = NativeModules;

export default LauncherMode as LauncherModeType;
