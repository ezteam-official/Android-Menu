#include "Offsets.h"
#include "Utils.h"
#include "SDK.h"

#define LIB_NAME "libil2cpp.so"

//-- Example to hook fields
void (*orig_function)(void* instance);
void ptr_function(void* instance) {

    if (instance) {
        *(float *) ((uintptr_t) instance + className::nameField) = Vars::playerSpeed;
    }

    return orig_function(instance);
}

void* hackThread(void *) {

    do {
        sleep(1);
    } while (!isLibraryLoaded(LIB_NAME));

    //-- Example to use Substrate Hook
    MSHookFunction((void *) KittyMemory::getAbsoluteAddress(LIB_NAME, Offset::updateAddress), (void*) ptr_function, (void **)& orig_function);

    return NULL;
}