#ifndef ANDROID_MENU_UTILS_H
#define ANDROID_MENU_UTILS_H

bool libLoaded = false;

bool isLibraryLoaded(const char *libraryName) {
    //libLoaded = true;
    char line[512] = {0};
    FILE *fp = fopen("/proc/self/maps", "rt");
    if (fp != NULL) {
        while (fgets(line, sizeof(line), fp)) {
            std::string a = line;
            if (strstr(line, libraryName)) {
                libLoaded = true;
                return true;
            }
        }
        fclose(fp);
    }
    return false;
}

#endif