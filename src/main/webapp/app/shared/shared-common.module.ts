import { NgModule } from '@angular/core';

import { YolseAppSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [YolseAppSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [YolseAppSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class YolseAppSharedCommonModule {}
