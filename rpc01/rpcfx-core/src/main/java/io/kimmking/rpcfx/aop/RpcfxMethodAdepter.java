package io.kimmking.rpcfx.aop;

import com.sun.xml.internal.ws.org.objectweb.asm.MethodAdapter;
import com.sun.xml.internal.ws.org.objectweb.asm.MethodVisitor;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/12/17 09:59
 */
public class RpcfxMethodAdepter extends MethodAdapter {
    public RpcfxMethodAdepter(MethodVisitor mv) {
        super(mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        //visitMethodInsn()
    }
}
