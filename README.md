## Version Notes
### 1.26.1
- Added package name to workout results Intent to fix a bug where results were not being sent to the phone.
- Replaced encrypted shared preferences with standard shared preferences as encryption was unnecessary at this time.
### 1.25.1
- Improved multithreading on network calls to prevent connection issues

## Confirmed Devices
**The following devices is what we test each release on to confirm stability:**
- Google Pixel Watch (WearOS 5)
- Google Pixel Watch 3 (WearOS 5)
- Samsung Galaxy Watch 4 (WearOS 4)
- Samsung Galaxy Watch 6 (WearOS 4)

Note: Other WearOS devices that meet the minimum Android SDK version should work but bugs may surface that we hadn't seen with our standard test devices.