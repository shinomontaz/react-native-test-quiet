
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNTestQuietSpec.h"

@interface TestQuiet : NSObject <NativeTestQuietSpec>
#else
#import <React/RCTBridgeModule.h>

@interface TestQuiet : NSObject <RCTBridgeModule>
#endif

@end
