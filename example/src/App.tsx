import * as React from 'react';
import { StyleSheet, View, Text } from 'react-native';
import LauncherMode from 'react-native-launcher-mode';

export default function App() {
  console.log(LauncherMode.resetPreferredLauncherAndOpenChooser());

  return (
    <View style={styles.container}>
      <Text>Result</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
