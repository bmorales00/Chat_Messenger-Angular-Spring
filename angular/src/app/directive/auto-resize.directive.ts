import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: 'textarea[autoResize]'
})
export class AutoResizeDirective {

  constructor(private elementRef:ElementRef) { }

  @HostListener('input')
  onInput(){
    this.resize();
  }

  private resize() {
    const textarea:HTMLTextAreaElement = this.elementRef.nativeElement;
    textarea.style.height = 'auto';
    textarea.style.height = '${textarea.scrollHeight}px';
  }
}
